import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable } from 'rxjs';
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
import { Router } from '../../testing-helpers/router-stubs';

import { User } from '../../models/user.model';
import{ MarketingStatus } from '../../models/marketing-status.model';
import { Associate } from '../../models/associate.model';

describe('HomeComponent', () => {
  let requestService: RequestService;
  let router: Router;
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
    let mockUser:User = new User('mockUser','pass',0,0);
    let associate1:Associate = new Associate('first','last',mockUser);
    let associate2:Associate = new Associate('first','last',mockUser);
    let associate3:Associate = new Associate('first','last',mockUser);
    
    let marketingStatus:MarketingStatus = new MarketingStatus(1, 'status');
    
    associate1.marketingStatus = marketingStatus;
    associate2.marketingStatus = marketingStatus;
    associate3.marketingStatus = marketingStatus;

    let associates:Associate[] = [associate1,associate2,associate3];

    let marketingStatus1:MarketingStatus = new MarketingStatus(4,'mockStatus', [associate1]);
    let marketingStatus2:MarketingStatus = new MarketingStatus(4,'mockStatus', [associate2]);
    let marketingStatus3:MarketingStatus = new MarketingStatus(4,'mockStatus', [associate3]);

    // associate1.marketingStatus = marketingStatus1;
    // associate2.marketingStatus = marketingStatus2;
    // associate3.marketingStatus = marketingStatus3;
    

    localStorage.setItem('currentAssociates',JSON.stringify(associates));

    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
