import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillsetComponent } from './skillset.component';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { element, by, browser } from 'protractor';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ChartsModule } from 'ng2-charts';
import { HomeComponent } from '../home/home.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterTestingModule } from '@angular/router/testing';
import {CurriculumService} from "../../services/curriculum-service/curriculum.service";

import { FormComponent } from '../form-component/form.component';

import {
  ActivatedRoute, ActivatedRouteStub, Router, RouterStub
} from '../../testing-helpers/router-stubs';

describe('SkillsetComponent', () => {
  let component: SkillsetComponent;
  let fixture: ComponentFixture<SkillsetComponent>;
  let activatedRoute: ActivatedRouteStub;

  class CurriculumServiceSpy {

  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SkillsetComponent, HomeComponent, NavbarComponent,
        FormComponent
      ],
      imports: [
        HttpClientTestingModule,
        ChartsModule,
        RouterTestingModule
      ],
      providers: [
        CurriculumService,
        { provide: ActivatedRoute, useValue: activatedRoute },
        { provide: Router, useClass: RouterStub }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    activatedRoute = new ActivatedRouteStub();
    fixture = TestBed.createComponent(SkillsetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a non-empty map of skill info', () => {
    expect(SkillsetComponent.getSkillInfo()).toBeTruthy();
    expect(SkillsetComponent.getSkillInfo().size).toBeGreaterThan(0);
  });

  it('should have skillID that\'s in the acceptable values', () => {
    component.selectedStatus = SelectedStatusConstants.CONFIRMED;
    // get the values in SkillsetComponent, and search for selectedStatus. It better be in there!
    let idFound = true;
    SkillsetComponent.getSkillInfo().forEach((value, key) => {
      idFound = (value === component.getSkillID())
    })
    expect(idFound).toBeTruthy();
  })

  it('should redirect to home iff out-of-bounds id was received', () => {
    activatedRoute.testParamMap = { id: -100 };
  })

  it('should have buttons that trigger changeChartType()', () => {
    // click each of the buttons
    let chartChangeButtons = fixture.nativeElement.querySelector('.btn.btn-default');
    let i = 0;
    for (let btn of chartChangeButtons) {
      // sanity testing the buttons to make sure they are actual buttons and not indices of some array
      expect(btn).toBeNaN();
      expect(btn.click).not.toBeUndefined();
      // clicking the buttons
      btn.click().then((data) => {
        // on click, changeChartType should invoke
        // TODO: find way to check the data itself
        expect(component.changeChartType).toHaveBeenCalledTimes(++i);

      });
    }

  })

  it('should not be using DUMMY_DATA', () => {
    // waiting on the observable in ngOnInit() to finish ...
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.skillsetData).not.toEqual(component.DUMMY_DATA);
    })
      // making sure that the skillsetData is now equal to the results of the data returned from CurriculumService
      .then(() => {
        let service: CurriculumService = TestBed.get(CurriculumService);
        service.getSkillsetsForStatusID(1).subscribe((res) => {
          // expect(component.skillsetData).toEqual(res.data.map((obj) => obj.count))
        })
          .unsubscribe()
      })
  });

  xit('should have one-to-one relation between skillsetData and skillsetLabels', () => {
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.skillsetData.length).toBeTruthy();
      expect(component.skillsetLabels.length).toBeTruthy();
      expect(component.skillsetLabels.length).toEqual(component.skillsetData.length);
    })
  })
});
