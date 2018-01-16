import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociateViewComponent } from './associate-view.component';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { AssociateService } from '../../services/associates-service/associates-service';

@Injectable()
export class MockActivatedRoute {
  private _id: {};
  getTestId() {
    return this._id;
  }
  setTestId(param: {}) {
    this._id = param;
  }

}
describe('AssociateViewComponent', () => {
  let component: AssociateViewComponent;
  let fixture: ComponentFixture<AssociateViewComponent>;
  let mockRoute: MockActivatedRoute;

  beforeEach(async(() => {
    mockRoute = new MockActivatedRoute();
    TestBed.configureTestingModule({
      declarations: [ AssociateViewComponent ],
      providers: [
        AuthenticationService,
        AssociateService,
        { provide: ActivatedRoute, userValue: mockRoute }
      ],
      imports: [HttpClientTestingModule, RouterTestingModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociateViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    mockRoute.setTestId({id: '2'});
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have an associate', () => {
    expect(component.associate).toBeTruthy();
  });
});
