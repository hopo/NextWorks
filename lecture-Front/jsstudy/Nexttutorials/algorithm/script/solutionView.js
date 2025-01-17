"use strict"


// ===================================
// template/solutionView.js
// ===================================


/*
 * Solution view
 */
function solutionView() {
    removeEditor(); // when load solution page, remoce Editior
    solutionDocu(); // call solutionDocu (make solution document)
}


/*
 *  Id chekc filter. between front and end
 */
function idFilter(tar, chk) {
    // return (chk.match(/^[qs][0-9]/g) == expr);
    return tar.slice(0, 2) == chk.slice(2);
}


/*
 * Remove editor(text area)
 */
function removeEditor() {
    groundDiv = document.querySelector("#groundDiv");
    groundDiv.removeChild(document.querySelector("#tareaDiv"));

    var inputs = groundDiv.getElementsByTagName("input");
    var lnth = inputs.length;

    for (var i = 0; i < lnth; i++) {
        groundDiv.removeChild(inputs[0]);
    }
}


/*
 * Solution part document
 */
function solutionDocu() {
    groundDiv = document.querySelector("#groundDiv");

    var lnth = db_solution.length;
    var r = null;

    for (var i = 0; i < lnth; i++) {
        r = lnth - 1 - i;
        var tar = db_solution[r].s_id;

        if (idFilter(tar, "00q" + g_selQzId)) {
            var eachSolDiv = document.createElement("div"); // ;; each sol repls Combo
            // nodeDiv = document.createElement("div");
            var nodePre = document.createElement("pre");

            eachSolDiv.setAttribute("class", "replCombo");
            eachSolDiv.setAttribute("id", tar);

            nodePre.setAttribute("class", "solDiv");
            nodePre.setAttribute("id", db_solution[r].s_id);
            nodePre.innerHTML = "[s_id: " + tar + "]<br>";
            nodePre.innerHTML += db_solution[r].code;

            eachSolDiv.appendChild(nodePre);
            eachSolDiv.appendChild(replyTextContent(tar.slice(3)));

            var repls = replysDocu(tar);
            for (var r of repls) {
                eachSolDiv.appendChild(r);
            }
        }
        groundDiv.appendChild(eachSolDiv);
    }


}


/*
 * Reply text content part document
 */
function replyTextContent(sidx) {
    nodeDiv = document.createElement("div");
    nodeDiv.setAttribute("class", "rpltextDiv");

    // ! reply input text
    nodeInput = document.createElement("input");

    nodeInput.setAttribute("type", "text");
    nodeInput.setAttribute("id", `s${sidx}`);
    nodeDiv.appendChild(nodeInput);

    // ! reply input button
    nodeInput = document.createElement("input");

    nodeInput.setAttribute("id", `s${sidx}`);
    nodeInput.setAttribute("type", "button");
    nodeInput.setAttribute("value", "댓글");
    nodeInput.setAttribute("onclick", `handleReplyReg(${sidx})`);

    nodeDiv.appendChild(nodeInput);

    return nodeDiv;
}


/*
 * Replys document about s_id @return nodeDiv Array
 */
function replysDocu(chk) {
    groundDiv = document.querySelector("#groundDiv");

    var lnth = db_reply.length;
    var r = null;
    var ls = [];

    for (var i = 0; i < lnth; i++) {
        r = lnth - 1 - i;
        var tar = db_reply[r].r_id;

        if (idFilter(tar, chk)) {
            nodeDiv = replyDivMaker(r);
            ls.push(nodeDiv);
        }
    }

    return ls;
}


/*
 * Reply div maker
 */
function replyDivMaker(idx) {
    nodeDiv = document.createElement("div");

    nodeDiv.setAttribute("class", "repDiv");
    nodeDiv.setAttribute("id", db_reply[idx].r_id);
    nodeDiv.innerHTML = db_reply[idx].repl;
    nodeDiv.innerHTML += "<br>by " + db_reply[idx].reg_id;
    nodeDiv.innerHTML += " (reg_date: " + db_reply[idx].reg_date + " )";

    return nodeDiv;
}


/*
 * Reply register control
 */
function handleReplyReg(sidx) {
    // ! push relply data at db_reply
    var rep = Object.create(reply);
    replyText = document.querySelector(`#s${sidx}`);

    rep.repl = replyText.value;
    replyText.value = ''; // ;;reset input text value
    rep.r_id = "s" + sidx + "r" + db_reply.length;
    rep.reg_id = "test_id";
    rep.reg_date = "18/06/29";

    db_reply.push(rep);

    // ! display latest reply (just registered it)
    var lnth = db_reply.length;
    nodeDiv = replyDivMaker(lnth - 1);

    groundDiv = document.querySelector("#groundDiv");
    var selectedSolDiv = groundDiv.querySelector(`#q${g_selQzId}s${sidx}`);

    selectedSolDiv.appendChild(nodeDiv);
}
