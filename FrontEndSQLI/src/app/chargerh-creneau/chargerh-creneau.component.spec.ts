import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChargerhCreneauComponent } from './chargerh-creneau.component';

describe('ChargerhCreneauComponent', () => {
  let component: ChargerhCreneauComponent;
  let fixture: ComponentFixture<ChargerhCreneauComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChargerhCreneauComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChargerhCreneauComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
