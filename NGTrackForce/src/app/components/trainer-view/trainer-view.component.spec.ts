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
import { LocalStorage } from '../../constants/local-storage';
import { MockAssociateService, MockAuthenticationService, MockTrainerService } from '../../testing-helpers/test-mock-services'

describe('TrainerViewComponent', () => {
  let component: TrainerViewComponent;
  let fixture: ComponentFixture<TrainerViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerViewComponent ],
      imports: [ FormsModule, HttpClientTestingModule, RouterTestingModule ],
      providers: [ 
        {provide: AuthenticationService, useClass: MockAuthenticationService}, 
        RequestService, 
        {provide: TrainerService, useClass: MockTrainerService}, 
        {provide: AssociateService, useClass: MockAssociateService} ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
      let mockUser = new User(LocalStorage.TEST_USER_NAME, LocalStorage.TEST_USER_PASSWORD, 0, 0);
      let mockTrainer = new Trainer(LocalStorage.TEST_USER_FIRST_NAME, LocalStorage.TEST_USER_LAST_NAME, mockUser);
      localStorage.setItem(LocalStorage.CURRENT_TRAINER_KEY, JSON.stringify(mockTrainer));


    fixture = TestBed.createComponent(TrainerViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
