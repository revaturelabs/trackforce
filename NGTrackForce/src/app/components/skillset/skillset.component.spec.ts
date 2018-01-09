import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillsetComponent } from './skillset.component';
import { SelectedStatuses } from '../../services/selectedStatuses';
import { element, by } from 'protractor';

describe('SkillsetComponent', () => {
  let component: SkillsetComponent;
  let fixture: ComponentFixture<SkillsetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SkillsetComponent ]
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
    expect(SkillsetComponent.SKILL_INFO).toBeTruthy();
    expect(SkillsetComponent.SKILL_INFO.size).toBeGreaterThan(0);
  });

  it('should have non-zero skillID', () => {
    component.selectedStatus = SelectedStatuses.CONFIRMED;
    expect(component.selectedStatus).toBeTruthy();
  })

  it('should have buttons that trigger changeChartType()', () => {
    // click each of the buttons
    // TODO: implement clicking the buttons and checking that changeChartType has been called, in a way that 
    //  doesn't break the code
    
  })
});
