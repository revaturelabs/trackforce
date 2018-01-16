import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ClientListComponent } from './client-list.component';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ClientListService } from '../../services/client-list-service/client-list.service';
import { SearchFilterPipe } from '../../pipes/search-filter/search-filter.pipe';
import { ChartsModule } from 'ng2-charts';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterTestingModule } from '@angular/router/testing';
import { RootComponent } from '../root/root.component';
import { HomeComponent } from '../home/home.component';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {RequestService} from '../../services/request-service/request.service';

describe('ClientListComponent', () => {
  let component: ClientListComponent;
  let fixture: ComponentFixture<ClientListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientListComponent, SearchFilterPipe, NavbarComponent, RootComponent, HomeComponent ],
      imports: [
        FormsModule,
        HttpClientTestingModule,
        ChartsModule,
        RouterTestingModule
      ],
      providers: [
        ClientListService,
        AuthenticationService,
        RequestService
      ]
    })
    .compileComponents();
  }));


  it('should create', async(() => {
    const fixture = TestBed.createComponent(ClientListComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
