import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';
import { AssociateSearchByTextFilter } from './associate-search-by-text-filter.pipes';


@NgModule({
  declarations: [CommonModule, AssociateSearchByTextFilter],
  providers: [],
  imports: [AssociateSearchByTextFilter]
})
export class AssociateSearchByTextFilterModule {}
