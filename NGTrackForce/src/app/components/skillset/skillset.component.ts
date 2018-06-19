import { Component, OnInit, Input } from '@angular/core';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { ChartScale } from '../../models/chart-scale.model';
import { CurriculumService } from '../../services/curriculum-service/curriculum.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { GraphCounts } from '../../models/graph-counts';

import { ThemeConstants } from '../../constants/theme.constants';
import { ChartOptions } from '../../models/ng2-charts-options.model';
import '../../constants/selected-status.constants';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { Color } from 'ng2-charts';


@Component({
  selector: 'app-skillset',
  templateUrl: './skillset.component.html',
  styleUrls: ['./skillset.component.css']
})

/**
 * The skillset component
 * @author : Michael Warren
 */
/** Decorator for automatically unsubscribing all observables upon ngDestory()
  *Prevents memory leaks
  */
@AutoUnsubscribe
export class SkillsetComponent implements OnInit {

  /**
   * Map of selected status to skill id
   */
  private static SKILL_INFO: Map<string, any>;

  /**
   * The types of charts
   */
  public static readonly chartTypes = {
    BAR: 'bar',
    PIE: 'pie',
    POLAR_AREA: 'polarArea'
  }

  /**
 * The sentry id for a status that doesn't exist
 */
  public static NULL = -1;

  /**
   * The selected status
   *@Input() allows a parent component to send data to the child via property-binding
   */
  @Input() selectedStatus = '';

  /**
   * The id of skill, probably to hit the API with
   */
  private skillID: number;

  /**
   * The flag that tells Angular, and the developer, whether or not ng2_chart dependency is actually being used
   */
  USE_NG2_CHART = true;
  /**
   * The dummy data to compare against for our tests
   */
  DUMMY_DATA = [{ data: [1, 1, 1, 1, 1], label: 'Mapped' }, { data: [1, 1, 1, 1, 1], label: 'Unmapped' }];

  /**
   * The type of chart
   */
  chartType = SkillsetComponent.chartTypes.BAR;
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
  chartOptions: { [k: string]: any } = {
    type: this.chartType,
    legend: {
      display: false
    },
    xAxes: [
      {
        ticks: {
          autoSkip: false
        }
      }
    ],
    scales: new ChartScale()
  };
  /**
   * The color scheme for the charts of this component
   */
  batchColors = ThemeConstants.BATCH_COLORS;


  public unmappedLabels = SelectedStatusConstants.UNMAPPED_LABELS;
  public skillColors: Array<Color> = ThemeConstants.SKILL_COLORS;
  public unmappedChartType = "pie";
  public unmappedOptions = ChartOptions.createOptionsTitle('Unmapped', 24, '#121212', 'right');
  public unmappedData: number[] = [0, 0, 0, 0];


  /**
    *@param {CurriculumService} CurriculumService
    * service for grabbing data from the back-end or mock back-end
    *
    *@param {ActivatedRoute} route
    * Allow parameters in the router url to be grabbed
    *
    *@param {Router} router
    *Allows to re-routing to other components
    *
    */
  constructor(private curriculumService: CurriculumService,
    private route: ActivatedRoute,
    private router: Router) {
    // setup SKILL_INFO
    if (!SkillsetComponent.SKILL_INFO) {
      SkillsetComponent.SKILL_INFO = new Map();
      SkillsetComponent.SKILL_INFO.set(SelectedStatusConstants.TRAINING, 6);
      SkillsetComponent.SKILL_INFO.set(SelectedStatusConstants.OPEN, 7);
      SkillsetComponent.SKILL_INFO.set(SelectedStatusConstants.SELECTED, 8);
      SkillsetComponent.SKILL_INFO.set(SelectedStatusConstants.CONFIRMED, 9);
      SkillsetComponent.SKILL_INFO.set(SelectedStatusConstants.DEPLOYED, 10);
    }
  }

  /**
   * Exposing SKILL_INFO in a safe way
   */
  public static getSkillInfo() {
    return SkillsetComponent.SKILL_INFO;
  }

  ngOnInit(): void {

    this.getUnmappedData();
    // console.log(this.unmappedData);

    // get skillID
    this.skillID = SkillsetComponent.SKILL_INFO.get(this.selectedStatus) || SkillsetComponent.NULL;
    // if we didn't get skillID from selectedStatus...
    if (this.skillID === SkillsetComponent.NULL) {
      // we get it from the ActivatedRoute params
      this.skillID = Number(this.route.snapshot.paramMap.get('id'));
      if (this.skillID < 6) {
        this.skillID += 6;  // TODO: remove this
      }
      // we now set selectedStatus
      SkillsetComponent.SKILL_INFO.forEach((value, key) => {
        if (value === this.skillID) {
          this.selectedStatus = key;
        }
      })
      // if there is empty string, simply go home
      if (!this.selectedStatus) {
        this.router.navigate(['/app-home']);
      }
    }
    // get the skillset data here
    this.curriculumService.getSkillsetsForStatusID(this.skillID).subscribe((data) => {
      // copy in the raw data into local variable
      const skillsets: GraphCounts[] = data;
      // map() that variable into skillsetData,skillsetLabels
      this.skillsetData = skillsets.map((obj) => { if (obj.count) { return obj.count } }).filter(this.isNotUndefined);
      this.skillsetLabels = skillsets.map((obj) => { if (obj.count) { return obj.name } }).filter(this.isNotUndefined);
      this.status = (((!this.skillsetLabels) || (!this.skillsetLabels.length)) &&
        ((!this.skillsetData) || (!this.skillsetData.length))) ?
        'There is no batch data on this status...' : 'Loaded!';
    });
  }

  /**
   * Changes the chart type of this component (does this really need explanation?!)
   */
  changeChartType(type: string) {
    this.chartType = type;
    // changing some chartOptions pre-emptively
    this.chartOptions.type = type;
    switch (type) {
      // if type is either PIE or POLAR_AREA...
      case SkillsetComponent.chartTypes.PIE:
      case SkillsetComponent.chartTypes.POLAR_AREA:
        // ... we're displaying the chart legend and on the right of the container
        this.chartOptions.legend = {
          display: true,
          position: 'right'
        };
        // ... and getting rid of the scales ...
        if (this.chartOptions.scales) { delete this.chartOptions.scales; }
        break;
      // otherwise, for BAR charts...
      case SkillsetComponent.chartTypes.BAR:
        // ...we give no legend...
        this.chartOptions.legend = {
          display: false
        };
        // ...but give scales...
        this.chartOptions.scales = new ChartScale();
        break;
    }
    // it's a mock, for right now
    return type;
  }

  public goToAssociateList(event) {
    if (event.active[0] !== undefined) {
      this.router.navigate([`associate-listing/curriculum/${this.skillsetLabels[event.active[0]._index]}/unmapped/${this.selectedStatus}`]);
    }
  }

  /**
   * Returns whether or not val is undefined. Used for filtering.
   * @param val The value to check for not undefined
   */
  public isNotUndefined(val): boolean {
    return val !== undefined;
  }

  /**
   * Exposing skillID in a safe way
   */
  public getSkillID(): number {
    return this.skillID;
  }

  public getUnmappedData() {
    this.unmappedData = JSON.parse(localStorage.getItem('unmappedData'));
    // console.log(this.unmappedData);
  }

  /**
 * @function UnmappedOnClick
 * @description When the "Unmapped" chart is clicked
 * the global variable selectedStatus is
 * set to the label of the slice
 * clicked.
 */
  unmappedOnClick(evt: any) {
    if (evt.active[0] !== undefined) {
      //navigate to skillset component
      this.router.navigate([`skillset/${evt.active[0]._index}`]);
      window.location.reload();
    }
  }


}
