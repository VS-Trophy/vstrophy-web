import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OffenseRosterTableComponent } from './offense-roster-table.component';

describe('OffenseRosterTableComponent', () => {
  let component: OffenseRosterTableComponent;
  let fixture: ComponentFixture<OffenseRosterTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OffenseRosterTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OffenseRosterTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
