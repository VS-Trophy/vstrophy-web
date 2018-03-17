import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopmatchesComponent } from './topmatches.component';

describe('TopmatchesComponent', () => {
  let component: TopmatchesComponent;
  let fixture: ComponentFixture<TopmatchesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopmatchesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopmatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
