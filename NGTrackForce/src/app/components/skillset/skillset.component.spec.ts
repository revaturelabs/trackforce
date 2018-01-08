import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillsetComponent } from './skillset.component';

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
});
