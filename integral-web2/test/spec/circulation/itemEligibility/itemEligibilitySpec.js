
var dependencies = [ 'app','angular', 'angular-mocks', 'jquery', 'angular-ui-router','angular-route','angular-sanitize','lodash',
		'angular-bootstrap', 'ng-grid', 'angular-flash', 'angular-dialog-service','i18n/localize','checklist-model','restangular', 'foundation/foundation', 'foundation/foundationController','services/navigationServices', 'services/services','global/global',
        'circulation/circulation','circulation/itemEligibility/itemEligibility',
        'repository/crudRepository'];
define(dependencies, function (app, angular, mocks) {
    describe('App module tests', function () {
        var ItemEligibility;
        var CategorySelectionModelFactory;
        beforeEach(module('integral.circulation'));
        beforeEach(function () {
            module(function ($provide) {
              $provide.value('integralMysticBaseUrl', 'integral-mystic');
              $provide.value('TOKEN', {'ticket': 'ticket'});
            });
        });
        beforeEach(inject(function (_ItemEligibility_, _CategorySelectionModelFactory_) {
            ItemEligibility = _ItemEligibility_;
            CategorySelectionModelFactory = _CategorySelectionModelFactory_;
        }));
        it("test marker", function () {
            expect(ItemEligibility).toBeDefined();
            expect(CategorySelectionModelFactory).toBeDefined();
        });
        
        it("get selection value", function () {
            var selections = [{id: 1, criteriaCode: "ic", criteriaValue: "-ic RS"}, {id:2, criteriaCode: "ic", criteriaValue: "-ic OS"}];
            var categoriesSameAsSelections = selections;
            var catategorySelectionModel = CategorySelectionModelFactory.newCategorySelectionModel(categoriesSameAsSelections, selections);
            expect("-ic ANY").toBe(catategorySelectionModel.getSelectionValue());
            
            var matchOneCategories = [selections[0]];
            var catategorySelectionModel = CategorySelectionModelFactory.newCategorySelectionModel(matchOneCategories, selections);
            expect("-ic RS").toBe(catategorySelectionModel.getSelectionValue());
            
            var emptyCategories = [];
            var catategorySelectionModel = CategorySelectionModelFactory.newCategorySelectionModel(emptyCategories, selections);
            expect("").toBe(catategorySelectionModel.getSelectionValue());
            
            var undefinedCategories = undefined;
            var catategorySelectionModel = CategorySelectionModelFactory.newCategorySelectionModel(undefinedCategories, selections);
            expect("").toBe(catategorySelectionModel.getSelectionValue());
        });
        
    });
});