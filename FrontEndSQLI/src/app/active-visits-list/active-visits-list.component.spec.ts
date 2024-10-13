import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveVisitsListComponent } from './active-visits-list.component';

describe('ActiveVisitsListComponent', () => {
  let component: ActiveVisitsListComponent;
  let fixture: ComponentFixture<ActiveVisitsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActiveVisitsListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActiveVisitsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
