import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {ChartsModule} from 'ng2-charts';
import { UndeployedComponent } from './undeployed.component';

fdescribe('UndeployedComponent', () => {
  let component: UndeployedComponent;
  let fixture: ComponentFixture<UndeployedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UndeployedComponent ],
      imports: [ChartsModule],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UndeployedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
