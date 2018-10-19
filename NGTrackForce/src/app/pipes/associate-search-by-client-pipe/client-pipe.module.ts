import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';
import { AssociateSearchByClientPipe } from './client-pipe.pipe';


@NgModule({
  declarations: [CommonModule, AssociateSearchByClientPipe],
  providers: [],
  imports: [AssociateSearchByClientPipe]
})
export class AssociateSearchByClientPipeModule {}
