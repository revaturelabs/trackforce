import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociateViewComponent } from './associate-view.component';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { AssociateService } from '../../services/associates-service/associates-service';

describe('AssociateViewComponent', () => {
  let component: AssociateViewComponent;
  let fixture: ComponentFixture<AssociateViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssociateViewComponent ],
      providers: [AuthenticationService, AssociateService],
      imports: [HttpClientTestingModule, RouterTestingModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociateViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have an associate', () => {
    expect(component.associate).toBeTruthy();
  });
});
