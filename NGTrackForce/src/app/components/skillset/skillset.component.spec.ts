import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NgModule, DebugElement, NgModuleFactoryLoader, Injector, Compiler } from '@angular/core';
import { SkillsetComponent } from './skillset.component';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
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

import { Observable } from 'rxjs';

import {
  ActivatedRoute, ActivatedRouteStub, RouterStub
} from '../../testing-helpers/router-stubs';
import { Batch } from '../../models/batch.model';
import { GraphCounts } from '../../models/graph-counts';

import { convertToParamMap, NavigationExtras, UrlSerializer, ChildrenOutletContexts } from '../../../../node_modules/@angular/router';
import { MatProgressSpinner, MatProgressSpinnerModule } from '../../../../node_modules/@angular/material';
import { CommonModule, Location } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { LocalStorageUtils } from '../../constants/local-storage';
import { Router } from '@angular/router';

@NgModule({
  declarations: [HomeComponent],
  entryComponents: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    ChartsModule,
    MatProgressSpinnerModule],
  exports: [
    HomeComponent,
    MatProgressSpinnerModule
  ]
})
class HomeModule {}

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
      g1.count = 22;

      return Observable.of([g1]);
    }
}

export class MockActivatedRoute {
  static createMockRoute(tid: number): any {
    return {
      route: {
      params: Observable.of({id: tid}),
      snapshot: {
        parent: {
          params: {
            id: 0
          }
        },
        paramMap: convertToParamMap({id: 0})
        }
    }};
  }
}

export class MockRouter {
  navigateByUrl(url: String) { return url;}
}

describe('SkillsetComponent', () => {
  let component: SkillsetComponent;
  let fixture: ComponentFixture<SkillsetComponent>;
  let activatedRoute : ActivatedRouteStub;
  let service = new MockCurriculumService(null);
  let router:Router;
  let spy: any;

  let routes = [
    {
        path: '',
        component: SkillsetComponent
    },
    {
      path: 'app-home',
      component: HomeComponent
    }
  ];
 
  beforeEach(async(() => {
    TestBed.resetTestingModule();
    
    TestBed.configureTestingModule({
      declarations: [
        SkillsetComponent,
        NavbarComponent,
        FormComponent
      ],
      imports : [
        HttpClientTestingModule,
        ChartsModule,
        RouterTestingModule.withRoutes(routes),
        FormsModule,
        HomeModule,
      ],
      providers : [
        {provide: CurriculumService, useClass: MockCurriculumService},
        {provide: Router, useClass: MockRouter},
        // { provide: ActivatedRoute, useValue: MockActivatedRoute.createMockRoute(0)},
      { provide : ActivatedRoute, useValue : {
        snapshot: {params: {id: 0},
                   paramMap: convertToParamMap({id: 0})}
  
        } },
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    localStorage.setItem(LocalStorageUtils.UNMAPPED_DATA_KEY, LocalStorageUtils.TEST_UNMAPPED_DATA_VALUE);
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
      // console.log('value: ' + SkillInfoValue.value);
      // console.log('skillID: ' + component.getSkillID());
      //console.log(parseInt(SkillInfoValue.value) === component.getSkillID());
      if (SkillInfoValue.value == component.getSkillID()) {
        idFound = true;
        break;
      }
      SkillInfoValue = SkillInfoIter.next();
    }
    let id = component.getSkillID();
    let count = SkillsetComponent.getSkillInfo().size;

    expect(idFound).toBeTruthy();//.toBeTruthy();
  })

  //This is the only test that fails in this suite right now. I can't tell exactly what the person who
  //originally created it had in mind, but my implementation is definitely wrong (since the router object isn't initialized). 
  //That said, feel free to take a stab at it! -Christina
  it('should redirect to home if out-of-bounds id was received', () => {
    const url = spyOn(router, 'navigateByUrl').calls.first().args[0];
    activatedRoute.testParamMap = { id: -100 };
    expect(url).toBe('/app-home');
  })

  it('should have buttons that trigger changeChartType()', () => {
    spy = spyOn(component, "changeChartType");
    let numOfBtns:number = 3;
    // click each of the buttons
    let els = fixture.debugElement.nativeElement;
    let btns = els.querySelectorAll('button');
    let i = 0;
    
    for (let btn of btns)
    {
      i++;
      // sanity testing the buttons to make sure they are actual buttons and not indices of some array
      expect(btn.click).not.toBeUndefined();
      // clicking the buttons
      btn.click();
    }

    expect(spy).toHaveBeenCalledTimes(numOfBtns);

  })

  it('should have one-to-one relation between skillsetData and skillsetLabels', () => {
      expect(component.skillsetData.length).toBeTruthy();
      expect(component.skillsetLabels.length).toBeTruthy();
      expect(component.skillsetLabels.length).toEqual(component.skillsetData.length);
  })

  it('chart type should be set by changeChartType method', () => {
    let type = "stuff";
    component.changeChartType(type);
    expect(component.chartType).toEqual(type);
  })

  it('should set proper legend and scales for pie, polar, and bar', () => {
    let type1 = "pie";
    let type2 = "polar";
    let type3 = "bar";
    
    component.changeChartType(type1);
    expect(component.chartOptions.legend).not.toBeNull();

    component.changeChartType(type2);
    expect(component.chartOptions.legend).not.toBeNull();

    component.chartOptions.scales = "";
    component.changeChartType(type1);
    expect(component.chartOptions.scales).toBeFalsy();

    component.changeChartType(type3);
    expect(component.chartOptions.legend).not.toBeNull();
    expect(component.chartOptions.scales).not.toBeNull();
  })

  it('isNotDefined method returns proper boolean', () => {
    let val1 = undefined;
    let val2 = "";

    expect(component.isNotUndefined(val1)).toBeFalsy();
    expect(component.isNotUndefined(val2)).toBeTruthy();
  })
});
