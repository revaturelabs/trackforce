import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';
import { AssociateSearchByStatusPipe } from './status-pipe.pipe';


@NgModule({
  declarations: [CommonModule, AssociateSearchByStatusPipe],
  providers: [],
  imports: [AssociateSearchByStatusPipe]
})
export class AssociateSearchByStatusPipeModule {}
