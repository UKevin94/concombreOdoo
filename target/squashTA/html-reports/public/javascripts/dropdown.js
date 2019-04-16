/*
 *     This file is part of the Squashtest platform.
 *     Copyright (C) 2011 - 2019 Henix
 *
 *     See the NOTICE file distributed with this work for additional
 *     information regarding copyright ownership.
 *
 *     This is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     this software is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with this software.  If not, see <http://www.gnu.org/licenses />.
 */
if (!window.getComputedStyle) {
    window.getComputedStyle = function(el, pseudo) {
        this.el = el;
        this.getPropertyValue = function(prop) {
            var re = /(\-([a-z]){1})/g;
            if (prop === 'float') prop = 'styleFloat';
            if (re.test(prop)) {
                prop = prop.replace(re, function () {
                    return arguments[2].toUpperCase();
                });
            }
            return el.currentStyle[prop] ? el.currentStyle[prop] : null;
        };
        return this;
    };
}

var toggleDisplay = function () {
        var elementId = String(this.name);
        
	var element = document.getElementById(elementId);
	var display = window.getComputedStyle(element).getPropertyValue('display');
        
	if (display === "none") {
		element.style.display = "table";
		document.getElementById(elementId + '-expand').style.display = "none";
		document.getElementById(elementId + '-collapse').style.display = "table-cell";
		document.getElementById(elementId + '-collapse').style.verticalAlign = "middle";
	} else if (display === "table") {
		element.style.display = "none";
		document.getElementById(elementId + '-expand').style.display = "table-cell";
		document.getElementById(elementId + '-expand').style.verticalAlign = "middle";
		document.getElementById(elementId + '-collapse').style.display = "none";
	}
};

var linkList = document.getElementsByClassName('toggleClass');

for (var i = 0; i < linkList.length; ++i) {
    linkList[i].onclick = toggleDisplay;
}