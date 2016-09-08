"use strict";
var router_1 = require('@angular/router');
var news_view_component_1 = require('./components/news-view/news-view.component');
var appRoutes = [
    {
        path: 'news',
        component: news_view_component_1.NewsViewComponent
    },
    {
        path: '',
        redirectTo: '/news',
        pathMatch: 'full'
    },
    {
        path: '**',
        redirectTo: '/news',
        pathMatch: 'full'
    }
];
exports.appRoutingProviders = [];
exports.routing = router_1.RouterModule.forRoot(appRoutes);
//# sourceMappingURL=app.routes.js.map