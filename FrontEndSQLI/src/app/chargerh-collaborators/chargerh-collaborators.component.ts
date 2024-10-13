import {Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../Services/auth.service";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {Chart, ArcElement, CategoryScale, ChartConfiguration, ChartData, Legend, Tooltip, PieController} from 'chart.js';

Chart.register(ArcElement,CategoryScale,Legend,Tooltip,PieController);

@Component({
  selector: 'app-chargerh-collaborators',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    FormsModule,
  ],
  templateUrl: './chargerh-collaborators.component.html',
  styleUrl: './chargerh-collaborators.component.css'
})
export class ChargerhCollaboratorsComponent implements OnInit{
  currentUser: any = {};
  collaborators: any[] = [];
  departmentStats: { department: string, count: number }[] = [];
  filteredCollaborators: any[] = [];
  @ViewChild('pieChart') pieChart!: ElementRef<HTMLCanvasElement>;

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.loadCurrentUser();
    this.loadCollaborators();
  }

  loadCurrentUser(){
    this.authService.getCurrentUser().subscribe({
      next: data => {

        this.currentUser = data;
      },
      error: error => {

      }
    });
  }

  loadCollaborators() {
    this.http.get<any[]>('http://localhost:8080/chargerh/collaborateurs').subscribe({
      next: data => {
        this.collaborators = this.filteredCollaborators = data;
        this.prepareDepartmentStats();
        },
     // error: error => console.error('Error fetching collaborators', error)
    });
  }

  prepareDepartmentStats() {
    const stats: Record<string, number> = {};
    this.collaborators.forEach(c => {
      stats[c.departement] = (stats[c.departement] || 0) + 1;
    });
    this.departmentStats = Object.keys(stats).map(key => ({ department: key, count: stats[key] }));
    this.initChart();
  }

  initChart() {
    if (this.pieChart && this.pieChart.nativeElement) {
      const ctx = this.pieChart.nativeElement.getContext('2d');
      if (ctx) {
        const data: ChartData<'pie'> = {
          labels: this.departmentStats.map(stat => stat.department),
          datasets: [{
            data: this.departmentStats.map(stat => stat.count),
            backgroundColor: this.departmentStats.map((_, index) => `hsl(${index * 360 / this.departmentStats.length}, 70%, 50%)`)
          }]
        };

        const config: ChartConfiguration<'pie'> = {
          type: 'pie',
          data: data,
          options: {
            responsive: true,
            plugins: {
              legend: {
                position: 'top'
              }
            }
          }
        };

        new Chart(ctx, config);
      }
    }
  }




  filterCollaborators(event: Event) {
    const input = event.target as HTMLInputElement;
    const value = input.value;
    this.filteredCollaborators = this.collaborators.filter(c =>
      c.nom.toLowerCase().includes(value.toLowerCase()) ||
      c.prenom.toLowerCase().includes(value.toLowerCase())
    );
  }

  logout() {
    this.authService.logout();
  }

}
