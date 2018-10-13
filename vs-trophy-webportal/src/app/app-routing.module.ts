import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ResultsViewComponent } from './results/results-view/results-view.component';
import { TeamsOverviewComponent } from './teams/teams-overview/teams-overview.component';
import { TeamInfoComponent } from './teams/team-info/team-info.component';
import { NewsItemEditorViewComponent } from './news/news-item-editor-view/news-item-editor-view.component';
import { NewsFeedViewComponent } from './news/news-feed-view/news-feed-view.component';
import { NewsItemDetailViewComponent } from './news/news-item-detail-view/news-item-detail-view.component';
import { ResultsOverviewComponent } from './results/results-overview/results-overview.component';
import { StatsViewComponent } from './stats/stats-view/stats-view.component';


const routes: Routes = [
  {path: '', redirectTo: 'news',pathMatch:'full'},
  {path: 'news', component: NewsFeedViewComponent},
  {path: 'news/:newsItemId', component: NewsItemDetailViewComponent},
  {path: 'newseditor', component: NewsItemEditorViewComponent},
  {path: 'results', component: ResultsOverviewComponent},
  {path: 'teams', component: TeamsOverviewComponent},
  {path: 'teams/:teamId', component: TeamInfoComponent},
  {path: 'stats', component: StatsViewComponent}
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})

export class AppRoutingModule { }
