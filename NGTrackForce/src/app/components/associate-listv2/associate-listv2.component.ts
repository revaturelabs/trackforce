import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { Associate } from '../../models/associate.model';
import { Filter } from './filter';

@Component({
  selector: 'app-associate-listv2',
  templateUrl: './associate-listv2.component.html',
  styleUrls: ['./associate-listv2.component.css']
})
export class AssociateListv2Component implements OnInit {

  // Tracks the current associates, this component has a 
  // copy of the data to be able to mutate data without side effects
  private filteredListOfAssociates: Associate[] = [];

  // This will track the filters selected by the user
  private appliedFilters: Filter[] = [];

  constructor(private associateTest: AssociateService) { }

  ngOnInit() {
  }

}
