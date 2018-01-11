import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ClientListComponent } from './client-list.component';
import { FormsModule } from '@angular/forms';
import { ClientListService } from '../../services/client-list-service/client-list.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SearchFilterPipe } from '../../pipes/search-filter/search-filter.pipe';

describe('ClientListComponent', () => {
  let component: ClientListComponent;
  let fixture: ComponentFixture<ClientListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientListComponent, SearchFilterPipe ],
      imports: [
        FormsModule,
        HttpClientTestingModule
      ],
      providers: [
        ClientListService
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
