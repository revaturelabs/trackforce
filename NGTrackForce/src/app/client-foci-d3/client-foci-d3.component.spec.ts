import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientFociD3Component } from './client-foci-d3.component';

describe('ClientFociD3Component', () => {
  let component: ClientFociD3Component;
  let fixture: ComponentFixture<ClientFociD3Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientFociD3Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientFociD3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
