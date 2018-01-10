import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ClientListComponent } from './client-list.component';
import { FormsModule } from '@angular/forms';
import { ClientService } from '../../services/clients-service/clients-service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('ClientListComponent', () => {
  let component: ClientListComponent;
  let fixture: ComponentFixture<ClientListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientListComponent ],
      imports: [
        FormsModule,
        HttpClientTestingModule
      ],
      providers: [
        ClientService
      ]
    })
    .compileComponents().then(() =>{
      
    });
  }));


  it('should create', async(() => {
    const fixture = TestBed.createComponent(ClientListComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
