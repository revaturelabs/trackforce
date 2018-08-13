import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {ChartsModule} from 'ng2-charts';
import { DeployedComponent } from './deployed.component';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { AssociateService } from '../../services/associate-service/associate.service';
import { HttpClient, HttpHandler } from '@angular/common/http';

describe('DeployedComponent', () => {
  let component: DeployedComponent;
  let fixture: ComponentFixture<DeployedComponent>;

  let routes = [
      {
          path: '',
          component: DeployedComponent
      }
  ]

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeployedComponent ],
      imports: [ChartsModule, RouterTestingModule.withRoutes(routes)],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [AssociateService, HttpClient, HttpHandler]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeployedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
