import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerViewComponent } from './trainer-view.component';
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { RequestService } from '../../services/request-service/request.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TrainerService } from '../../services/trainer-service/trainer.service';


describe('TrainerViewComponent', () => {
  let component: TrainerViewComponent;
  let fixture: ComponentFixture<TrainerViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerViewComponent ],
      imports: [ FormsModule, HttpClientTestingModule, RouterTestingModule ],
      providers: [ AuthenticationService, RequestService, TrainerService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainerViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
