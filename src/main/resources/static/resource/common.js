$('select[data-value]').each(function(index, el){
	const $el = $(el);
	
	const defaultValue = $el.attr('data-value');
	$el.val(defaultValue);
});