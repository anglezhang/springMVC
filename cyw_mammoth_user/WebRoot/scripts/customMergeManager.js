var __extends = this.__extends || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    __.prototype = b.prototype;
    d.prototype = new __();
};
var wijmo;
(function (wijmo) {
    (function (_grid) {
        'use strict';

        var CustomMergeManager = (function (_super) {
            __extends(CustomMergeManager, _super);
            function CustomMergeManager(grid, mergeKey) {
                _super.call(this, grid);

                this._mergeKey = mergeKey;
            }
            Object.defineProperty(CustomMergeManager.prototype, "mergeKey", {
                set: function (value) {
                    this._mergeKey = value;
                },
                enumerable: true,
                configurable: true
            });

            CustomMergeManager.prototype._mergeCell = function (p, r1, c1, r2, c2) {
                var row1 = p.rows[r1], row2 = p.rows[r2], mergeState = _super.prototype._mergeCell.call(this, p, r1, c1, r2, c2), mergeKey = this._mergeKey;

                if (mergeState && this._g.allowMerging) {
                    if (wijmo.isString(mergeKey) && this._g.itemsSource) {
                        if (row1.dataItem[mergeKey] !== row2.dataItem[mergeKey]) {
                            mergeState = false;
                        }
                    } else if (wijmo.isNumber(this._mergeKey)) {
                        if (p.getCellData(r1, mergeKey, false) !== p.getCellData(r2, mergeKey, false)) {
                            mergeState = false;
                        }
                    }
                }

                return mergeState;
            };
            return CustomMergeManager;
        })(_grid.MergeManager);
        _grid.CustomMergeManager = CustomMergeManager;
    })(wijmo.grid || (wijmo.grid = {}));
    var grid = wijmo.grid;
})(wijmo || (wijmo = {}));
//# sourceMappingURL=customGrid.js.map
