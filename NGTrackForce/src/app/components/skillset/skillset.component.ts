import { Component, OnInit, Input } from '@angular/core';
import { SelectedStatuses } from '../../services/selectedStatuses';
import { AutoUnsubscribe } from '../../autoUnsubscribe';
import { ChartScale } from '../chart-properties/scale.properties';

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
  @Input() selectedStatus : string;
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
   * The skillset data
   * 
   */
  skillsetData = [];
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
    ]
  };
  
  constructor() {
    // setup SKILL_INFO
    if (!SkillsetComponent.SKILL_INFO) {
      SkillsetComponent.SKILL_INFO = new Map();
      SkillsetComponent.SKILL_INFO.set(SelectedStatuses.TRAINING, 6);
      SkillsetComponent.SKILL_INFO.set(SelectedStatuses.OPEN, 7);
      SkillsetComponent.SKILL_INFO.set(SelectedStatuses.SELECTED, 8);
      SkillsetComponent.SKILL_INFO.set(SelectedStatuses.CONFIRMED, 9);
    }
  }
  
  ngOnInit(): void {
    // get skillID
    this.skillID = SkillsetComponent.SKILL_INFO.get(this.selectedStatus) || 0;

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
