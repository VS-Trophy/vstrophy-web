import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecordResultViewComponent } from './record-result-view.component';

describe('RecordResultViewComponent', () => {
  let component: RecordResultViewComponent;
  let fixture: ComponentFixture<RecordResultViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecordResultViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecordResultViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
