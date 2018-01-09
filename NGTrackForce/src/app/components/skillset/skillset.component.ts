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
  static SKILL_INFO : Map<String, any>;
  /**
   * The id of skill, probably to hit the API with
   */
  private skillID : number;

  
  
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

  changeChartType(type : String)
  {
    // it's a mock, for right now
    return type;
  }
  

}
