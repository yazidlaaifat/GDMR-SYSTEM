import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChargerhDashboardComponent } from './chargerh-dashboard.component';

describe('ChargerhDashboardComponent', () => {
  let component: ChargerhDashboardComponent;
  let fixture: ComponentFixture<ChargerhDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChargerhDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChargerhDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
