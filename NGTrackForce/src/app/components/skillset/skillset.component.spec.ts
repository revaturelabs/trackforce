import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillsetComponent } from './skillset.component';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { element, by, browser } from 'protractor';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ChartsModule } from 'ng2-charts';
import { RootComponent } from '../root/root.component';
import { HomeComponent } from '../home/home.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterTestingModule } from '@angular/router/testing';
import { SkillsetService } from '../../services/skill-set-service/skill-set.service';

describe('SkillsetComponent', () => {
  let component: SkillsetComponent;
  let fixture: ComponentFixture<SkillsetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SkillsetComponent, RootComponent, HomeComponent, NavbarComponent ],
      imports : [
        HttpClientTestingModule, 
        ChartsModule,
        RouterTestingModule
      ],
      providers : [
        SkillsetService
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
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

  it('should have non-zero skillID', () => {
    component.selectedStatus = SelectedStatusConstants.CONFIRMED;
    expect(component.selectedStatus).toBeTruthy();
  })

  it('should have buttons that trigger changeChartType()', () => {
    // click each of the buttons
    let chartChangeButtons = fixture.nativeElement.querySelector('.btn.btn-default');
    let i = 0;
    for (let btn of chartChangeButtons)
    {
      // sanity testing the buttons to make sure they are actual buttons and not indices of some array
      expect(btn).toBeNaN(); 
      btn.click().then((data) => {
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
    // making sure that the skillsetData is now equal to the results of the data returned from SkillsetService
    .then(() => {
      let service : SkillsetService = TestBed.get(SkillsetService);
      service.getSkillsetsForStatusID(1).subscribe((res) => {
        expect(component.skillsetData).toEqual(res.data.map((obj) => obj.count))
      })
      .unsubscribe()
    })
  });
  
  it('should have one-to-one relation between skillsetData and skillsetLabels', () => {
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.skillsetData.length).toBeTruthy();
      expect(component.skillsetLabels.length).toBeTruthy();
      expect(component.skillsetLabels.length).toEqual(component.skillsetData.length);
    })    
  })
});
