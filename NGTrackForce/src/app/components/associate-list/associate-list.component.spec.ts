import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociateListComponent } from './associate-list.component';
import { AssociateService } from '../../services/associates-service/associates-service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ClientListService } from '../../services/client-list-service/client-list.service';


describe('AssociateListComponent', () => {
  let component: AssociateListComponent;
  let fixture: ComponentFixture<AssociateListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssociateListComponent ],
      imports: [
        HttpClientTestingModule
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
