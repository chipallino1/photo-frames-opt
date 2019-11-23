ALTER TABLE photo_frames ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE photo_frames ADD FOREIGN KEY (currency_id) REFERENCES currency(id);

ALTER TABLE photo_frames_on_colors ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);
ALTER TABLE photo_frames_on_colors ADD FOREIGN KEY (color_id) REFERENCES colors(id);

ALTER TABLE photo_frames_on_carts ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);
ALTER TABLE photo_frames_on_carts ADD FOREIGN KEY (cart_id) REFERENCES cart(id);

ALTER TABLE photo_frames_on_photos ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);
ALTER TABLE photo_frames_on_photos ADD FOREIGN KEY (photo_id) REFERENCES photos(id);

ALTER TABLE photo_frames_on_sizes ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);
ALTER TABLE photo_frames_on_sizes ADD FOREIGN KEY (size_id) REFERENCES sizes(id);

ALTER TABLE cart ADD FOREIGN KEY (user_id) REFERENCES users(id);