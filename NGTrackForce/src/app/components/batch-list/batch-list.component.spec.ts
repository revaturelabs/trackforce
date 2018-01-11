import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {BatchListComponent} from './batch-list.component';
import {ChartsModule} from 'ng2-charts';
import {AuthenticationService} from '../../services/authentication/authentication.service';
import {BatchService} from '../../services/batch/batch.service';
import {RouterModule} from '@angular/router';
import {appRoutes} from '../../routing/routes';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {HomeComponent} from '../home/home.component';
import {ClientMappedComponent} from '../client-mapped/client-mapped.component';
import {AssociateListComponent} from '../associate-list/associate-list.component';
import {LoginComponent} from '../login/login.component';

TestBed.configureTestingModule({
  declarations: [
    BatchListComponent,
    HomeComponent,
    ClientMappedComponent,
    AssociateListComponent,
    LoginComponent
  ],
  providers: [
    BatchService,
    AuthenticationService,
    /* todo add interceptor */
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    FormsModule,
    BrowserModule,
    HttpClientModule,
    ChartsModule
  ]
}).compileComponents()
  .then((data) => {

    describe('BatchListComponent', () => {
      let component: BatchListComponent;
      let fixture: ComponentFixture<BatchListComponent>;

      beforeEach(() => {
        fixture = TestBed.createComponent(BatchListComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
      });

      it('should create', () => {
        expect(component).toBeTruthy();
      });
    });

  }, console.log);
