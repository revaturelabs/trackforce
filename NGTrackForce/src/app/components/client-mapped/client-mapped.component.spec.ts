import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ClientMappedComponent } from './client-mapped.component';
import { ChartsModule } from 'ng2-charts';
import { ClientMappedService } from '../../services/client-mapped-service/client-mapped-service';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterTestingModule } from '@angular/router/testing';

describe('ClientMappedComponent', () => {
  let component: ClientMappedComponent;
  let fixture: ComponentFixture<ClientMappedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientMappedComponent, NavbarComponent ],
      imports: [ChartsModule, HttpClientTestingModule, RouterTestingModule],
      providers: [ClientMappedService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientMappedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
