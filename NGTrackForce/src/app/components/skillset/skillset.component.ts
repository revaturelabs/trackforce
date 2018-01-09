import { Component, OnInit, Input } from '@angular/core';
import { SelectedStatuses } from '../../services/selectedStatuses';

@Component({
  selector: 'app-skillset',
  templateUrl: './skillset.component.html',
  styleUrls: ['./skillset.component.css']
})

/**
 * The skillset component
 * @author : Michael Warren
 */
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
   * The chart options
   */
  chartOptions : {[k: string]: any} = {
    type : this.chartType,
    legend : {
      display : false
    }
  };
  
  constructor() {
    // setup SKILL_INFO
    if (!SkillsetComponent.SKILL_INFO)
    {
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

  changeChartType(type : string)
  {
    // TODO: change this to actual business logic
    this.chartType = type;
    switch (type)
    {
      case SkillsetComponent.chartTypes.PIE:
      case SkillsetComponent.chartTypes.POLAR_AREA:
        this.chartOptions = {
            type:type, 
            legend: {
                display : true,
                position: 'right'
            },
            xAxes:[
              {
                ticks: {
                  autoSkip:false
                }
              }
            ]
        };

        return;
      case SkillsetComponent.chartTypes.BAR:

        this.chartOptions = { 
          type:type,
          legend: {
            display:false
          },
          xAxes:[
            {
              ticks: {
                autoSkip:false
              }
            }
          ],
          scales: 
            {
              yAxes: [
                {
                  ticks: {
                    min:0
                  }
                }
              ]
            }
          };
        return;
    }
    // it's a mock, for right now
    return type;
  }
  
  public getSkillInfo()
  {
    return SkillsetComponent.SKILL_INFO;
  }

}
