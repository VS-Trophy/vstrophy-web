import functools

def check_pipeline(process_item_method):

    @functools.wraps(process_item_method)
    def wrapper(self, item, spider):

       # if the pipelines class property matches the item, we execute the method     
        if self.itemclass is item.__class__:
            return process_item_method(self, item, spider)
        else:
            return item

    return wrapper