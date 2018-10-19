import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';
import { SearchFilterPipe } from './search-filter.pipe';


@NgModule({
  declarations: [CommonModule, SearchFilterPipe],
  providers: [],
  exports: [SearchFilterPipe]
})
export class SearchFilterPipeModule {}
