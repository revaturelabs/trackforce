import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerViewComponent } from './trainer-view.component';
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { RequestService } from '../../services/request-service/request.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TrainerService } from '../../services/trainer-service/trainer.service';
import { AssociateService } from '../../services/associate-service/associate.service';
import { User } from '../../models/user.model';
import { Trainer } from '../../models/trainer.model';


describe('TrainerViewComponent', () => {
  let component: TrainerViewComponent;
  let fixture: ComponentFixture<TrainerViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerViewComponent ],
      imports: [ FormsModule, HttpClientTestingModule, RouterTestingModule ],
      providers: [ AuthenticationService, RequestService, TrainerService, AssociateService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
      let mockUser = new User("mockUsername", "mockPassword", 0, 0);
      let mockTrainer = new Trainer("mockFirstName", "mockLastName", mockUser);
      localStorage.setItem('currentTrainer',JSON.stringify(mockTrainer));


    fixture = TestBed.createComponent(TrainerViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
