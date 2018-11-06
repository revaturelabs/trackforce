import { SearchFilterPipeModule } from './../../pipes/search-filter/search-filter.module';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { AssociateService } from './../../services/associate-service/associate.service';
import { ClientService } from './../../services/client-service/client.service';
import { RequestService } from './../../services/request-service/request.service';
import { HttpClientModule } from '@angular/common/http';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { NavbarComponent } from './../navbar/navbar.component';
import { NotFoundComponent } from './../not-found/not-found.component';
import { AssociateViewComponent } from './../associate-view/associate-view.component';
import { InvalidSessionComponent } from './../invalid-session/invalid-session.component';
import { LoginComponent } from './../login/login.component';
import { RouterTestingModule } from '@angular/router/testing';
import { AppRoutingModule } from './../../routing/app-routing.module';
import { FormComponent } from './../form-component/form.component';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociateListPageComponent } from './associate-list-page.component';
import { FormsModule } from '@angular/forms';
import { MatDialog, MatDialogModule } from '@angular/material';
import { CommonModule } from '@angular/common';
import { AssociateListRoutingModule } from './associate-list-page-routing.module';
import { OverlayModule } from '@angular/cdk/overlay';

describe('AssociateListPageComponent', () => {
  let component: AssociateListPageComponent;
  let fixture: ComponentFixture<AssociateListPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
     declarations: [
        AssociateListPageComponent,
        LoginComponent,
        InvalidSessionComponent,
        FormComponent,
        AssociateViewComponent,
        NotFoundComponent,
        NavbarComponent
      ],
      providers: [
        RequestService,
        AssociateService,
        ClientService
      ],
      imports:[
        FormsModule,
        AppRoutingModule,
        RouterTestingModule,
        MatProgressSpinnerModule,
        HttpClientModule,
        OverlayModule,
        MatDialogModule
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociateListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
