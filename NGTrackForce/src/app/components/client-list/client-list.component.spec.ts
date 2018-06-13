import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {ClientListComponent} from './client-list.component';
import {FormsModule} from '@angular/forms';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {ClientService} from '../../services/client-service/client.service';
import {SearchFilterPipe} from '../../pipes/search-filter/search-filter.pipe';
import {ChartsModule} from 'ng2-charts';
import {NavbarComponent} from '../navbar/navbar.component';
import {RouterTestingModule} from '@angular/router/testing';
import {RootComponent} from '../root/root.component';
import {HomeComponent} from '../home/home.component';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {RequestService} from '../../services/request-service/request.service';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";

describe('ClientListComponent', () => {
  let component: ClientListComponent;
  let fixture: ComponentFixture<ClientListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        ClientListComponent,
        SearchFilterPipe,
        NavbarComponent,
        RootComponent,
        HomeComponent
      ],
      imports: [
        FormsModule,
        HttpClientTestingModule,
        ChartsModule,
        RouterTestingModule
      ],
      providers: [
        ClientService,
        AuthenticationService,
        RequestService
      ],
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA
      ]
    });

    fixture = TestBed.createComponent(ClientListComponent);
    component = fixture.debugElement.componentInstance;
  });


  it('should create', async(() => {
    expect(component).toBeTruthy();
  }));
});
