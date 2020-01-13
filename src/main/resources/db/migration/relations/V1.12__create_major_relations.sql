ALTER TABLE photo_frames ADD FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE photo_frames_on_colors ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);
ALTER TABLE photo_frames_on_colors ADD FOREIGN KEY (color_id) REFERENCES colors(id);

ALTER TABLE photo_frames_on_carts ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);
ALTER TABLE photo_frames_on_carts ADD FOREIGN KEY (cart_id) REFERENCES cart(id);

ALTER TABLE photo_frames_on_sizes ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);
ALTER TABLE photo_frames_on_sizes ADD FOREIGN KEY (size_id) REFERENCES sizes(id);

ALTER TABLE cart ADD FOREIGN KEY (client_id) REFERENCES users(id);

ALTER TABLE orders ADD FOREIGN KEY (client_id) REFERENCES users(id);
ALTER TABLE orders ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);

ALTER TABLE photo_frames_on_orders ADD FOREIGN KEY (order_id) REFERENCES orders(id);
ALTER TABLE photo_frames_on_orders ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);

ALTER TABLE photo_frames_common ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);

ALTER TABLE discounts ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);