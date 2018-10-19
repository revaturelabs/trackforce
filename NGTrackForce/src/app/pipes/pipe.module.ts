import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';
import { SearchFilterPipe } from './search-filter/search-filter.pipe';
import { AssociateSearchByClientPipe } from './associate-search-by-client-pipe/client-pipe.pipe';
import { AssociateSearchByStatusPipe } from './associate-search-by-status-pipe/status-pipe.pipe';
import { AssociateSearchByTextFilter } from './associate-search-by-text-filter/associate-search-by-text-filter.pipes';

@NgModule({
  declarations: [SearchFilterPipe, CommonModule, AssociateSearchByClientPipe, AssociateSearchByStatusPipe, AssociateSearchByTextFilter],
  providers: [],
  imports: [SearchFilterPipe, AssociateSearchByClientPipe, AssociateSearchByStatusPipe, AssociateSearchByTextFilter]
})
export class PipeModule {}
