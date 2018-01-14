import { Component, OnInit, Input } from '@angular/core';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { AutoUnsubscribe } from '../../decorator/auto-unsubscribe.decorator';
import { ChartScale } from '../../models/chart-scale.model';
import { SkillsetService } from '../../services/skill-set-service/skill-set.service';
import { NgZone } from '@angular/core';
import { ThemeConstants } from '../../constants/theme.constants';
import { ActivatedRoute } from '@angular/router';

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
  private static SKILL_INFO : Map<String, any>;
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
  skillsetData = this.DUMMY_DATA;
  /**
   * THe skillset labels
   */
  skillsetLabels = [];
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
  batchColors: string[] = ThemeConstants.BATCH_COLORS;
  
  constructor(private skillsetService : SkillsetService, 
      private route : ActivatedRoute,
      private zone : NgZone) {
    // setup SKILL_INFO
    if (!SkillsetComponent.SKILL_INFO) {
      SkillsetComponent.SKILL_INFO = new Map();
      SkillsetComponent.SKILL_INFO.set(SelectedStatusConstants.TRAINING, 6);
      SkillsetComponent.SKILL_INFO.set(SelectedStatusConstants.OPEN, 7);
      SkillsetComponent.SKILL_INFO.set(SelectedStatusConstants.SELECTED, 8);
      SkillsetComponent.SKILL_INFO.set(SelectedStatusConstants.CONFIRMED, 9);
      SkillsetComponent.SKILL_INFO.set('', 0);
    }
  }
  
  ngOnInit(): void {
    // get skillID
    this.skillID = SkillsetComponent.SKILL_INFO.get(this.selectedStatus) || 0;
    if (!this.skillID)
    {
      this.skillID = Number(this.route.snapshot.paramMap.get('id'));
    }
    // get the skillset data here
    this.skillsetService.getSkillsetsForStatusID(this.skillID).subscribe((res) => {
      // copy in the raw data into local variable
      let skillsets : Array<any> = res.data;
      // map() that variable into skillsetData,skillsetLabels
      this.skillsetData  = skillsets.map((obj) => {if (obj.count) return obj.count}).filter((val) => val !== undefined);
      this.skillsetLabels= skillsets.map((obj) => {if (obj.count) return obj.name}).filter((val) => val !== undefined);
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
   * Exposing SKILL_INFO in a safe way
   */
  public static getSkillInfo() {
    return SkillsetComponent.SKILL_INFO;
  }

}
