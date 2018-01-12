import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociateListComponent } from './associate-list.component';
import { AssociateService } from '../../services/associates-service/associates-service';
import { ClientListService } from '../../services/client-list-service/client-list.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { AssociateSearchByTextFilter } from '../../pipes/associate-search-by-text-filter/associate-search-by-text-filter.pipes';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterTestingModule } from '@angular/router/testing';
import { RootComponent } from '../root/root.component';
import { HomeComponent } from '../home/home.component';


describe('AssociateListComponent', () => {
  let component: AssociateListComponent;
  let fixture: ComponentFixture<AssociateListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssociateListComponent, AssociateSearchByTextFilter, NavbarComponent, RootComponent, HomeComponent ],
      imports: [
        HttpClientTestingModule,
        FormsModule,
        RouterTestingModule
      ],
      providers: [
        AssociateService,
        ClientListService
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociateListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
