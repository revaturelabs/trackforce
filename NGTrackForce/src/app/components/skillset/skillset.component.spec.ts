import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillsetComponent } from './skillset.component';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { element, by, browser } from 'protractor';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ChartsModule } from 'ng2-charts';
import { HomeComponent } from '../home/home.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterTestingModule } from '@angular/router/testing';
//import { CurriculumService } from '../../services/skill-set-service/skill-set.service';
import {CurriculumService} from "../../services/curriculum-service/curriculum.service";
import { Curriculum } from '../../models/curriculum.model';
import { FormComponent } from '../form-component/form.component';
// added imports; DK
import { FormsModule } from '@angular/forms';

import { Observable } from 'rxjs/Observable';

import {
  ActivatedRoute, ActivatedRouteStub, Router, RouterStub
} from '../../testing-helpers/router-stubs';
import { Batch } from '../../models/batch.model';
import { GraphCounts } from '../../models/graph-counts';

import { SSL_OP_PKCS1_CHECK_1 } from 'constants';
import { convertToParamMap } from '../../../../node_modules/@angular/router';

export class MockCurriculumService extends CurriculumService {
    getAllCurricula(): Observable<Curriculum[]> {
      let c1:Curriculum = new Curriculum();
      c1.id = 1;
      c1.name = 'mockCurriculum';
      c1.batches = [new Batch()];

      let curriculum:Curriculum[] = [c1];
    
      return Observable.of(curriculum);
    }

    getSkillsetsForStatusID(statusID: number): Observable<GraphCounts[]> {

      let g1:GraphCounts = new GraphCounts();
      g1.id = 1;
      g1.name = 'mockGraphCounts';
      g1.count = 20;

      return Observable.of([g1]);
    }
}

export class MockActivatedRoute {

}

describe('SkillsetComponent', () => {
  let component: SkillsetComponent;
  let fixture: ComponentFixture<SkillsetComponent>;
  let activatedRoute : ActivatedRouteStub;



  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ 
        SkillsetComponent,
        HomeComponent,
        NavbarComponent,
        FormComponent
      ],
      imports : [
        HttpClientTestingModule,
        ChartsModule,
        RouterTestingModule, 
        FormsModule
      ],
      providers : [
        CurriculumService,
        { provide : ActivatedRoute, useValue : {
          snapshot: {params: {id: 6},
                     paramMap: convertToParamMap({id: 6})}                    
   
        } },
        { provide : Router,         useClass : RouterStub }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    localStorage.setItem('unmappedData',JSON.stringify([1,2,3,4]));
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
    let SkillInfoIter = SkillsetComponent.getSkillInfo().values();
    let SkillInfoValue = SkillInfoIter.next();
    let idFound = false;

    // Nathan: 8/13/2018 SkillID of component.getSkillID()
    // should be one of the values present in getSkillInfo()

    // SkillsetComponent.getSkillInfo().forEach((value, key) => {
    //   idFound = (value === component.getSkillID())
    // })

    while(!SkillInfoValue.done) {
      console.log('value: ' + SkillInfoValue.value);
      console.log('skillID: ' + component.getSkillID());
      console.log(parseInt(SkillInfoValue.value) === component.getSkillID());
      if (SkillInfoValue.value == component.getSkillID()) {
        idFound = true;
        break;
      }
    }
    let id = component.getSkillID();
    let count = SkillsetComponent.getSkillInfo().size;

    expect(idFound).toBeTruthy();//.toBeTruthy();
  })

  it('should redirect to home if out-of-bounds id was received', () => {
    activatedRoute.testParamMap = { id: -100 };
  })

  it('should have buttons that trigger changeChartType()', () => {
    // click each of the buttons
    let chartChangeButtons = fixture.nativeElement.querySelector('.btn.btn-default');
    let i = 0;
    for (let btn of chartChangeButtons)
    {
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
      let service : CurriculumService = TestBed.get(CurriculumService);
      service.getSkillsetsForStatusID(1).subscribe((res) => {
        expect(component.skillsetData).toEqual(res.map((obj) => obj.count))
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
