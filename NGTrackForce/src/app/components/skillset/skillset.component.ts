import { Component, OnInit, Input } from '@angular/core';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { AutoUnsubscribe } from '../../decorator/auto-unsubscribe.decorator';
import { ChartScale } from '../../models/chart-scale.model';
import { SkillsetService } from '../../services/skill-set-service/skill-set.service';
import { NgZone } from '@angular/core';
import { ThemeConstants } from '../../constants/theme.constants';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-skillset',
  templateUrl: './skillset.component.html',
  styleUrls: ['./skillset.component.css']
})

/**
 * The skillset component
 * @author : Michael Warren
 */
@AutoUnsubscribe
export class SkillsetComponent implements OnInit {
  /**
   * The selected status 
   */
  @Input() selectedStatus : string = '';
  /**
   * Map of selected status to skill id
   */
  private static SKILL_INFO : Map<string, any>;
  // TODO: remove this
  private static NEW_SKILL_INFO : Map<string, any>;
  /**
   * The id of skill, probably to hit the API with
   */
  private skillID : number;
  /**
   * The flag that tells Angular, and the developer, whether or not ng2_chart dependency is actually being used
   */
  USE_NG2_CHART : boolean = true;
  /**
   * The types of charts
   */
  public static readonly chartTypes = {
    BAR : 'bar',
    PIE : 'pie',
    POLAR_AREA : 'polarArea'
  }
  /**
   * The type of chart
   */
  chartType = SkillsetComponent.chartTypes.BAR;
  /**
   * The dummy data to compare against for our tests
   */
  DUMMY_DATA = [{data:[1,1,1,1,1], label: 'Mapped'},{data:[1,1,1,1,1],label: 'Unmapped'}];
  /**
   * The skillset data
   */
  skillsetData = [];
  /**
   * The skillset labels
   */
  skillsetLabels = [];
  /**
   * The status of the component
   */
  status = "Loading...";
  /**
   * The chart options, as a JavaScript-style object, and pre-initialized so as to DRY up our code...
   */
  chartOptions : {[k: string]: any} = {
    type : this.chartType,
    legend : {
      display : false
    },
    xAxes:[
      {
        ticks: {
          autoSkip:false
        }
      }
    ],
    scales : new ChartScale()
  };
  /** 
   * The color scheme for the charts of this component 
   */
  batchColors = ThemeConstants.BATCH_COLORS;
  /**
   * The sentry id for a status that doesn't exist
   */
  public static NULL = -1;

  constructor(private skillsetService : SkillsetService, 
      private route  : ActivatedRoute,
      private router : Router) {
    // setup SKILL_INFO
    if (!SkillsetComponent.SKILL_INFO) {
      SkillsetComponent.SKILL_INFO = new Map();
      SkillsetComponent.SKILL_INFO.set(SelectedStatusConstants.TRAINING, 6);
      SkillsetComponent.SKILL_INFO.set(SelectedStatusConstants.OPEN, 7);
      SkillsetComponent.SKILL_INFO.set(SelectedStatusConstants.SELECTED, 8);
      SkillsetComponent.SKILL_INFO.set(SelectedStatusConstants.CONFIRMED, 9);
      SkillsetComponent.SKILL_INFO.set(SelectedStatusConstants.DEPLOYED, 10);
    }
    //TODO: remove this
    if (!SkillsetComponent.NEW_SKILL_INFO) {
      SkillsetComponent.NEW_SKILL_INFO = new Map();
      SkillsetComponent.NEW_SKILL_INFO.set(SelectedStatusConstants.TRAINING, 0);
      SkillsetComponent.NEW_SKILL_INFO.set(SelectedStatusConstants.OPEN, 1);
      SkillsetComponent.NEW_SKILL_INFO.set(SelectedStatusConstants.SELECTED, 2);
      SkillsetComponent.NEW_SKILL_INFO.set(SelectedStatusConstants.CONFIRMED, 3);
      SkillsetComponent.NEW_SKILL_INFO.set(SelectedStatusConstants.DEPLOYED, 4);
    }
  }
  
  ngOnInit(): void {
    // get skillID
    this.skillID = SkillsetComponent.SKILL_INFO.get(this.selectedStatus) || SkillsetComponent.NULL;
    // if we didn't get skillID from selectedStatus...
    if (this.skillID === SkillsetComponent.NULL)
    {
      // we get it from the ActivatedRoute params
      this.skillID = Number(this.route.snapshot.paramMap.get('id'));
      if (this.skillID < 6) this.skillID += 6;  // TODO: remove this
      // we now set selectedStatus
      SkillsetComponent.SKILL_INFO.forEach((value, key) => { 
        if (value === this.skillID) this.selectedStatus = key;
      })
      // if there is empty string, simply go home
      if (!this.selectedStatus)
      {
        // this.route.snapshot.
      }
    }
    // get the skillset data here
    this.skillsetService.getSkillsetsForStatusID(this.skillID).subscribe((data) => {
      // copy in the raw data into local variable
      let skillsets : Array<any> = data;
      // map() that variable into skillsetData,skillsetLabels
      this.skillsetData  = skillsets.map((obj) => {if (obj.count) return obj.count}).filter(this.isNotUndefined);
      this.skillsetLabels= skillsets.map((obj) => {if (obj.count) return obj.name}).filter(this.isNotUndefined);
      this.status = (((!this.skillsetLabels) || (!this.skillsetLabels.length)) &&
        ((!this.skillsetData) || (!this.skillsetData.length))) ? 
          'There is no batch data on this status...' : 'Loaded!';
    });
  }

  /**
   * Changes the chart type of this component (does this really need explanation?!)
   */
  changeChartType(type : string) {
    this.chartType = type;
    // changing some chartOptions pre-emptively
    this.chartOptions.type = type;
    switch (type) {
      // if type is either PIE or POLAR_AREA...
      case SkillsetComponent.chartTypes.PIE:
      case SkillsetComponent.chartTypes.POLAR_AREA:
        // ... we're displaying the chart legend and on the right of the container
        this.chartOptions.legend = {
          display : true,
          position: 'right'
        };
        // ... and getting rid of the scales ...
        if (this.chartOptions.scales) delete this.chartOptions.scales;
        break;
      // otherwise, for BAR charts...
      case SkillsetComponent.chartTypes.BAR:
        // ...we give no legend...
        this.chartOptions.legend = {
          display:false
        };
        // ...but give scales...
        this.chartOptions.scales = new ChartScale();
        break;
    }
    // it's a mock, for right now
    return type;
  }

  
  
  /**
   * Returns whether or not val is undefined. Used for filtering.
   * @param val The value to check for not undefined
   */
  public isNotUndefined(val) : boolean { return val !== undefined; }

  /**
   * Exposing SKILL_INFO in a safe way
   */
  public static getSkillInfo() {
    return SkillsetComponent.SKILL_INFO;
  }

}
