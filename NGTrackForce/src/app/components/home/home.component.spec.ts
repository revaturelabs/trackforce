import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeComponent } from './home.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { RequestService } from '../../services/request-service/request.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { SkillsetComponent } from '../skillset/skillset.component';
import { ClientMappedComponent } from '../client-mapped/client-mapped.component';
// added imports; DK
import { ChartsModule } from 'ng2-charts';
import { FooterComponent } from '../footer/footer.component';
import { DataSyncService } from '../../services/datasync-service/data-sync.service';
import { BatchService } from '../../services/batch-service/batch.service';
import { ClientService } from '../../services/client-service/client.service';
import { AssociateService } from '../../services/associate-service/associate.service';
import { CurriculumService } from '../../services/curriculum-service/curriculum.service';
import { UserService } from '../../services/user-service/user.service';


describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeComponent, NavbarComponent, HomeComponent, SkillsetComponent, ClientMappedComponent, FooterComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule, ChartsModule],
      providers: [ RequestService, AuthenticationService, DataSyncService, BatchService, ClientService, AssociateService, CurriculumService, UserService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
