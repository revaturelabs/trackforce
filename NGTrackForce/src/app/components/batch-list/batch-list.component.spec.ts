import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {BatchListComponent} from './batch-list.component';
import {ChartsModule} from 'ng2-charts';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {BatchService} from '../../services/batch-service/batch.service';
import {RouterModule} from '@angular/router';
import {appRoutes} from '../../routing/routes';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {HomeComponent} from '../home/home.component';
import {ClientMappedComponent} from '../client-mapped/client-mapped.component';
import {AssociateListComponent} from '../associate-list/associate-list.component';
import {LoginComponent} from '../login/login.component';
import { ClientListComponent } from '../client-list/client-list.component';
import { CreateUserComponent } from '../create-user/create-user.component';
import { SearchFilterPipe } from '../../pipes/search-filter/search-filter.pipe';
import { AssociateSearchByTextFilter } from '../../pipes/associate-search-by-text-filter/associate-search-by-text-filter.pipes';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterTestingModule } from '@angular/router/testing';
import { RootComponent } from '../root/root.component';
import {FormComponent} from '../form-component/form.component';
import {SkillsetComponent} from '../skillset/skillset.component';

TestBed.configureTestingModule({
  declarations: [
    BatchListComponent,
    HomeComponent,
    ClientMappedComponent,
    ClientListComponent,
    AssociateListComponent,
    LoginComponent,
    CreateUserComponent,
    SearchFilterPipe,
    AssociateSearchByTextFilter,
    NavbarComponent,
    RootComponent,
    FormComponent,
    SkillsetComponent
  ],
  providers: [
    BatchService,
    AuthenticationService,
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    RouterTestingModule,
    FormsModule,
    BrowserModule,
    HttpClientModule,
    ChartsModule
  ]
}).compileComponents()
  .then((data) => {

    describe('BatchListComponent', async() => {
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

      it('should generate pull some data', () => {
        fixture.detectChanges();
        fixture.whenStable().then(() => {
          expect(component.batches.length).toBeGreaterThan(0);
        })
      })
    });

  }, console.log);
