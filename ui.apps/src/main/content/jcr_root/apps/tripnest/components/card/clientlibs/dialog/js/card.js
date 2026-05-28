(function ($, document) {

    $(document).on("foundation-contentloaded", function () {

        var checkbox = $("[name='./openInNewTab']");
        var cardLinkWrapper = $("[name='./cardLink']")
            .closest(".coral-Form-fieldwrapper");

        function toggleCardLink() {
            // CHANGE: Added debugging and more robust selector handling
            console.log("Checkbox checked:", checkbox.prop("checked"));
            console.log("Card link wrapper found:", cardLinkWrapper.length);

            if (checkbox.prop("checked")) {
                cardLinkWrapper.show();
                console.log("Showing card link");
            } else {
                cardLinkWrapper.hide();
                console.log("Hiding card link");
            }
        }


        // CHANGE: Added initial state check
        if (checkbox.length && cardLinkWrapper.length) {
            toggleCardLink();
            checkbox.on("change", toggleCardLink);
        } else {
            console.error("Checkbox or card link wrapper not found");
        }

    });

})(Granite.$, document);