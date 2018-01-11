import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BatchListComponent } from './batch-list.component';
import {ChartsModule} from 'ng2-charts';
import {AuthenticationService} from '../../services/authentication/authentication.service';
import {BatchService} from '../../services/batch/batch.service';

describe('BatchListComponent', () => {
  let component: BatchListComponent;
  let fixture: ComponentFixture<BatchListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BatchListComponent ],
      providers: [ BatchService, AuthenticationService /* todo add interceptor */ ],
      imports: [ChartsModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BatchListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
